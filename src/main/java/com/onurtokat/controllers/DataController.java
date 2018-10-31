package com.onurtokat.controllers;

import com.onurtokat.domain.DataDomain;
import com.onurtokat.init.DataInit;
import com.onurtokat.services.DataService;
import com.onurtokat.storage.StorageFileNotFoundException;
import com.onurtokat.storage.StorageService;
import com.onurtokat.utility.HeaderConverter;
import com.onurtokat.utility.UnzipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**DataController provides GET mapping HTTP services for showing data
 * as HTML table and update data and configuration files using file upload pages.
 *
 * @author onurtokat
 */
@Controller
public class DataController {

    private DataService dataService;
    private StorageService storageService;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    @Autowired
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * listUploadedFiles GET mapping method provides file list of the
     * uploaded files with label file for model. Uploaded file is
     * transferred to the serveFile method using mvcUriComponentsContributor.
     * Thus, file URI can be shared with serveFile method.
     *
     * @param model is used for adding file URI as attribute
     * @return uploadForm page
     * @throws IOException is used for when file exceptions occur
     */
    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files",
                storageService.loadAll()
                        .map(path -> MvcUriComponentsBuilder
                                .fromMethodName(DataController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                        .collect(Collectors.toList()));

        return "uploadForm";
    }

    /**
     * serveFile GET mapping method uses the filename with its absolute path
     * to serve it as HTTP link on the page
     *
     * @param filename is achieved from the URL as parameter and is used for path definition
     * @return HTTP entity to be served as HTTP link on the page
     * @throws IOException is used for when file does not exist situation
     */
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFile().getAbsolutePath() + "\"").body(file);
    }

    /**
     * handleFileUpload POST mapping method is used for storing uploaded file with
     * store() method. Storing path is defined in the StorageProperties class as location.
     * At the same time, fileLoader() method is used to load file line by line to mongo db.
     * If an exception is thrown, when data reading and loading, user is redirected to
     * fileError.html page to inform about error. If data is loaded to embedded db successfully,
     * result message is written to page with file name as http link. Via http link file can
     * be accessable and downloadable.
     *
     * @param file MultipartFile
     * @param redirectAttributes attributes which will be used for informing
     * @return localhost:8080, file uploader main page
     */
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        storageService.store(file);
        try {
            UnzipFile.unzipFiles("upload-dir/" + file.getOriginalFilename());
            DataInit.fileLoader(DataInit.INITIAL_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
            return "/fileError";
        }
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    /**
     * handleStorageFileNotFound method is used for exception handling when
     * file not found exception
     *
     * @param exc is used for as expected thrown exception
     * @return is HttpStatus.NOT_FOUND of the response field
     */
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @ResponseBody
    @GetMapping("/data")
    public String getDataAsTable() {

        List<DataDomain> dataList = dataService.listAll();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"1\">\n" +
                "<tr>");

        for (Map.Entry<String, String> entry : new TreeMap<String, String>(HeaderConverter.getHeaders()).entrySet()) {
            sb.append("<th>" + entry.getValue() + "</th>");
        }
        sb.append("</tr>");
        for (DataDomain dataDomain : dataList) {
            sb.append("<tr>");
            sb.append("<th>" + dataDomain.getId() + "</th>");
            for(String value:dataDomain.getDetails()){
                sb.append("<th>" + value + "</th>");
            }
            sb.append("</tr>");
        }
        return sb.toString();
    }
}
