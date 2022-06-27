package com.rest.api.mprestclientdemo;

import com.rest.api.models.Post;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/posts")
/*
@RegisterRestClient allows Quarkus to know
that this interface is meant to be available for
CDI injection as a REST Client
 */
@RegisterRestClient
public interface PostService {

    @GET
    List<Post> fetchAllPosts();
}