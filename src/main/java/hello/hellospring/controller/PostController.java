package hello.hellospring.controller;

import hello.hellospring.domain.Post;
import hello.hellospring.domain.User;
import hello.hellospring.service.PostService;
import hello.hellospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private static final String UPLOAD_PATH = "C:\\Users\\user\\Documents\\study\\hello-spring\\src\\main\\resources\\image";

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    @ResponseBody
    public List<Post> getAllPosts(){
        return postService.findPosts();
    }

    @PostMapping("/post")
    @ResponseBody
    public Object create(PostForm form, @RequestParam MultipartFile file) throws IOException {
        Post post = new Post();
        post.setUserId(form.getUserId());
        post.setTitle(form.getTitle());
        post.setContents(form.getContents());
        post.setImg_path(saveFile(file));

        long postId = postService.create(post);
        Map<String, Long> result = new HashMap<>();
        result.put("postId", postId);

        return result;
    }

    private String saveFile(MultipartFile file) throws IOException {
        UUID uuid = UUID.randomUUID();
        String img_path = uuid + "_" + file.getOriginalFilename();

        File dest = new File(UPLOAD_PATH, file.getOriginalFilename());
        file.transferTo(dest);

        return img_path;
    }

    @DeleteMapping("/post/{id}")
    @ResponseBody
    public String deletePost(@PathVariable(name = "id") Long id) {
        return postService.deletePost(id);
    }
}
