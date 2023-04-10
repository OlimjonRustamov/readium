package uz.o_rustamov.readium.files;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.files.model.Attachment;
import uz.o_rustamov.readium.files.model.AttachmentContent;
import uz.o_rustamov.readium.files.repository.AttachmentContentRepository;
import uz.o_rustamov.readium.files.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

import static uz.o_rustamov.readium.Constants.NOT_FOUND;
import static uz.o_rustamov.readium.Constants.SUCCESS;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController("/api/file")
public class AttachmentController {

    AttachmentRepository attachmentRepository;
    AttachmentContentRepository contentRepository;

    public AttachmentController(AttachmentRepository attachmentRepository, AttachmentContentRepository contentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.contentRepository = contentRepository;
    }

    @PostMapping(value = "/upload")
    public HttpEntity<ApiResponse> uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();
            attachment.setOriginalName(originalFilename);
            attachment.setContentType(contentType);
            attachment.setFileSize(size);
            attachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            try {
                attachmentContent.setBytes(file.getBytes());
            } catch (IOException e) {
                return ResponseEntity.status(406).body(new ApiResponse(e.getMessage(), 406, null));
            }
            attachmentContent.setAttachment(attachment);
            attachmentContent = contentRepository.save(attachmentContent);
            return SUCCESS;
        }
        return NOT_FOUND;
    }

    @GetMapping(value = "/info/{id}")
    public HttpEntity<ApiResponse> getAttachmentById(@PathVariable long id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent())
            return ResponseEntity.ok(new ApiResponse(null, 200, optionalAttachment.get()));
        else return NOT_FOUND;
    }

    @GetMapping(value = "/download/{id}")
    public void downloadById(@PathVariable long id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalContent =
                    contentRepository.findByAttachment_Id(attachment.getId());
            if (optionalContent.isPresent()) {
                AttachmentContent content = optionalContent.get();
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + attachment.getOriginalName() + "\"");

                response.setContentType(attachment.getContentType());

                FileCopyUtils.copy(content.getBytes(), response.getOutputStream());
            }
        }
    }
}
