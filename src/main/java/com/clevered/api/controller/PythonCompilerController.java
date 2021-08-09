package com.clevered.api.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clevered.rest.api.vo.CodeRequest;
import com.clevered.rest.api.vo.CodeResponse;

/**
 * 
 * @author Nagendra Kumar
 *
 */
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*", originPatterns = "*")
@RequestMapping("/v4/python")
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

	@GetMapping("/thello")
	@ResponseBody
	public String djdj() {
		return "Hello MNe";
	}

	@PostMapping("/test100")
	@ResponseBody
	public CodeResponse test100() {
		CodeResponse codeResponse = new CodeResponse();
		codeResponse.setDescription("Testing");
		codeResponse.setInput("1920");
		codeResponse.setOutput("9303");
		codeResponse.setStatus("success");
		return codeResponse;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/compile")
	@ResponseBody
	public synchronized CodeResponse compileCodeOnline(@RequestBody CodeRequest codeRequest) {

		LOGGER.debug("pcode  [{}] ", codeRequest.getCode());
		boolean drawGraph = false;
		if (codeRequest.getCode().contains(".show()")) {
			drawGraph = true;
		}

		// replacing code .show() with savefig method to save the file
		codeRequest.setCode(codeRequest.getCode().replace(".show()", ".savefig(\"graph100.png\")"));
		// Writing input into input.txt
		String codeOutput = "The is some problem at server while compiling code!";
		try {

			Files.write(Paths.get("Main.py"), codeRequest.getCode().getBytes());
			codeOutput = crunProcess(pythonVersionCommand + " Main.py");
			if (codeOutput.contains("Main.py")) {
				codeOutput = codeOutput.substring(codeOutput.indexOf("Main.py"));
			}

			if (drawGraph) {
				// Reading the generate image/graph
				byte[] graphs = Files.readAllBytes(Paths.get("graph100.png"));
				String encodedString = Base64.getEncoder().encodeToString(graphs);
				codeOutput = codeOutput + "chartstart" + encodedString + "chartend";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		CodeResponse codeResponse = new CodeResponse();
		codeResponse.setInput("");
		codeResponse.setOutput(codeOutput);
		if (codeOutput.contains("Main.py")) {
			codeResponse.setStatus("fail");
		} else {
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
