package dev.malipan;

import dev.malipan.config.SecurityConfig;
import dev.malipan.repo.UserAppRepo;
import dev.malipan.resource.AuthController;
import dev.malipan.resource.HomeController;
import dev.malipan.service.TokenService;
import dev.malipan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;


@WebMvcTest({HomeController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class, UserService.class, UserAppRepo.class})
class SpringSecurityJwtAuthApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void cicd_test(){
        System.out.println("Yes my test works !");
    }

    @Test
    public void rootWhenUnauthenticatedThen401() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void rootWhenAuthenticatedThenSayHelloUser() throws Exception {
        MvcResult result = this.mvc.perform(post("/token")
                        .with(httpBasic("malipan", "password")))
                .andExpect(status().isOk())
                .andReturn();
        this.mvc.perform(get("/").header("Authorization", "Bearer "+result.getResponse().getContentAsString()))
                .andExpect(content().string("Hello, malipan!"));
    }

    @Test
    @WithMockUser
    public void rootWithMockUserStatusIsOk() throws Exception {

        this.mvc.perform(get("/")).andExpect(status().isOk());
    }

}
