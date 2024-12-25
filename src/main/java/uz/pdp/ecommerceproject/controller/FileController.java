package uz.pdp.ecommerceproject.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.ecommerceapp.entity.Attachment;
import uz.pdp.ecommerceapp.entity.AttachmentContent;
import uz.pdp.ecommerceapp.repo.AttachmentContentRepository;
import uz.pdp.ecommerceapp.repo.AttachmentRepository;

import java.io.IOException;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;



    @GetMapping("/view/{attachmentId}")
    public void viewImage(@PathVariable Integer attachmentId, HttpServletResponse response) throws IOException {
        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("Fayl topilmadi"));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment)
                .orElseThrow(() -> new RuntimeException("Kontent topilmadi"));
        response.getOutputStream().write(attachmentContent.getContent());
    }
}
