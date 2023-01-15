package com.rest.api.mprestclientdemo;

import com.rest.api.models.Post;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;


@Path("/api/v1/mprestclient")
public class PostResource {

    @Inject
    @RestClient
    PostService postService;

    @GET
    @Path("/posts")
    public List<Post> fetchAllPosts() {
        return postService.fetchAllPosts();
    }
}
