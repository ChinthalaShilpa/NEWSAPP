//package com.newsapp.authenticationserver.controller;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.newsapp.authenticationserver.exception.InvalidTokenException;
//import com.newsapp.authenticationserver.request.LoginRequest;
//import com.newsapp.authenticationserver.service.UserServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ContextConfiguration(classes = {AuthenticationController.class})
//@ExtendWith(SpringExtension.class)
//class AuthenticationControllerDiffblueTest {
//    @Autowired
//    private AuthenticationController authenticationController;
//
//    @MockBean
//    private UserServiceImpl userServiceImpl;
//
//    /**
//     * Method under test:  {@link AuthenticationController#doLogin(LoginRequest)}
//     */
//    @Test
//    void testDoLogin() throws Exception {
//        when(userServiceImpl.validateUserService(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
//
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setPassword(null);
//        loginRequest.setUserName("janedoe");
//        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authenticationController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Please send valid username and password\"}"));
//    }
//
//    /**
//     * Method under test: {@link AuthenticationController#getUserName(String)}
//     */
//    @Test
//    void testGetUserName() throws InvalidTokenException {
//        //   Diffblue Cover was unable to write a Spring test,
//        //   so wrote a non-Spring test instead.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalStateException: Invocation failure
//        //   Controller [com.newsapp.authenticationserver.controller.AuthenticationController]
//        //   Method [public org.springframework.http.ResponseEntity<java.lang.String> com.newsapp.authenticationserver.controller.AuthenticationController.getUserName(java.lang.String) throws com.newsapp.authenticationserver.exception.InvalidTokenException] with argument values:
//        //    [0] [type=java.lang.String] [value=Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==]
//        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
//        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
//        //   java.lang.IllegalStateException: Invocation failure
//        //   Controller [com.newsapp.authenticationserver.controller.AuthenticationController]
//        //   Method [public org.springframework.http.ResponseEntity<java.lang.String> com.newsapp.authenticationserver.controller.AuthenticationController.getUserName(java.lang.String) throws com.newsapp.authenticationserver.exception.InvalidTokenException] with argument values:
//        //    [0] [type=java.lang.String] [value=Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==]
//        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
//        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
//        //   com.newsapp.authenticationserver.exception.InvalidTokenException: Missing or Invalid Authentication Header
//        //       at com.newsapp.authenticationserver.controller.AuthenticationController.getUserNameFromToken(AuthenticationController.java:99)
//        //       at com.newsapp.authenticationserver.controller.AuthenticationController.getUserName(AuthenticationController.java:65)
//        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
//        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        assertThrows(InvalidTokenException.class, () -> (new AuthenticationController()).getUserName("ABC123"));
//        assertThrows(InvalidTokenException.class, () -> (new AuthenticationController()).getUserName(null));
//        assertThrows(InvalidTokenException.class, () -> (new AuthenticationController()).getUserName("Bearer"));
//        assertThrows(InvalidTokenException.class, () -> (new AuthenticationController()).getUserName("BearerABC123"));
//        assertThrows(InvalidTokenException.class, () -> (new AuthenticationController())
//                .getUserName("Bearercom.newsapp.authenticationserver.controller.AuthenticationController"));
//        assertThrows(InvalidTokenException.class,
//                () -> (new AuthenticationController()).getUserName("Bearerjava.lang.String"));
//    }
//
//    /**
//     * Method under test:  {@link AuthenticationController#doLogin(LoginRequest)}
//     */
//    @Test
//    void testDoLogin2() throws Exception {
//        when(userServiceImpl.validateUserService(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
//
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setPassword("iloveyou");
//        loginRequest.setUserName(null);
//        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authenticationController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Please send valid username and password\"}"));
//    }
//}
