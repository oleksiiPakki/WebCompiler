package io.teamdev.javaclasses.servlets;


import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.runtime.ProgramExecutionException;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.language.AlmostNormalLanguageCompiler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        super.doGet(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");

        Stream<String> lines = request.getReader().lines();

        try {

            response.getOutputStream().print(calculate(lines));

        } catch (ProgramExecutionException | DeadLockException e) {

            response.getOutputStream().print("<span style=\"color: red;\">" +e.getMessage()+"</span>");
        }

    }

    private static String calculate(Stream<String> input) throws ProgramExecutionException, DeadLockException {
        String code = input.collect(Collectors.joining(System.lineSeparator()));

        AlmostNormalLanguageCompiler compiler = new AlmostNormalLanguageCompiler();
        RuntimeEnvironment runtimeEnvironment = new RuntimeEnvironment();

        compiler.evaluate(code,runtimeEnvironment);

        return runtimeEnvironment.output().toString();
    }

}


