package com.clevered.api.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clevered.rest.api.vo.CodeResponse;

/**
 * 
 * @author Nagendra Kumar
 *
 */
@Controller
@RequestMapping("/python")
public class PythonCompilerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PythonCompilerController.class);

	@Value("${app.base.url}")
	private String appBaseUrl;
	
	@Value("${python.version.command}")
	private String pythonVersionCommand;

	private static String cprintLines(InputStream ins) throws Exception {
		StringBuilder builder = new StringBuilder();
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			builder.append(line + "\n");
		}
		return builder.toString();
	}

	private static String crunProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);
		String output = "no";
		try {
			output = printLines(pro.getInputStream());
			if (output == null || output.length() == 0) {
				output = printLines(pro.getErrorStream());
			}
			pro.waitFor();
			LOGGER.debug(command + " exitValue() " + pro.exitValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/compile/execute/code")
	@ResponseBody
	public synchronized CodeResponse compileCodeOnline(Model model, @RequestParam("pcode") String pcode) {
		LOGGER.debug("pcode  [{}] ", pcode);
		// Writing input into input.txt
		String generatedOutput = "NA";
		String codeOutput = "The is some problem at server while compiling code!";
		try {
			Files.write(Paths.get("Main.py"), pcode.getBytes());
			codeOutput = crunProcess(pythonVersionCommand+" Main.py");
			if (codeOutput.contains("Main.py")) {
				codeOutput = codeOutput.substring(codeOutput.indexOf("Main.py"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		CodeResponse codeResponse = new CodeResponse();
		codeResponse.setInput("");
		codeResponse.setOutput(codeOutput);
		if(codeOutput.contains("Main.py")) {
			codeResponse.setStatus("fail");			
		}else {
			codeResponse.setStatus("success");	
		}
		codeResponse.setDescription(codeOutput);
		LOGGER.debug("_________________________");
		LOGGER.info(codeResponse.toString());
		LOGGER.debug("_________________________");
		return codeResponse;
	}

	private static String printLines(InputStream ins) throws Exception {
		StringBuilder builder = new StringBuilder();
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			builder.append(line + "\n");
		}
		return builder.toString();
	}

}