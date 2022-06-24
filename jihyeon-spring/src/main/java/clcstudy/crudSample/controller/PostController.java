package clcstudy.crudSample.controller;

import clcstudy.crudSample.domain.Post;
import clcstudy.crudSample.domain.User;
import clcstudy.crudSample.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    @ResponseBody
    public List<Post> getAllPost(){
        return postService.findPosts();
    }

    @PostMapping("/post")
    @ResponseBody
    public Object upload(@RequestParam("title") String title,
                         @RequestParam("contents") String contents,
                         @RequestParam("userId") Long userId,
                         @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        PostImg img = new PostImg(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
        File newFileName = new File(img.getUuid() + "_" + img.getFileName());
        file.transferTo(newFileName);

        Post post = new Post();
        post.setTitle(title);
        post.setContents(contents);
        post.setUserId(userId);
        post.setImgPath(newFileName.toString());

        Long postId = postService.upload(post);

        Map<String, Long> result = new HashMap<>();
        result.put("userId", postId);

        return result;
    }
}
