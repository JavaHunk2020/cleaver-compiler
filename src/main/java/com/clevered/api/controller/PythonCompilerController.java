package com.clevered.api.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.server.UID;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

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
	
	@Value("${flask.clevered.home}")
	private String flaskCleveredHome;
	
	@Value("${flask.clevered.index}")
	private String flaskCleveredIndex;
	
	@Value("${flask.clevered.first.home}")
	private String flaskCleveredFirstHome;
	
	@Value("${flask.clevered.second.home}")
	private String flaskCleveredSecondHome;
	

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


	/**
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/compile")
	@ResponseBody
	public synchronized CodeResponse compileCodeOnline(@RequestBody CodeRequest codeRequest) {
		CodeResponse codeResponse = new CodeResponse();
		// LOGGER.debug("pcode  [{}] ", codeRequest.getCode());
		boolean drawGraph = false;
		
		if (codeRequest.getCode().contains("'index.html'")) {
			codeResponse.setIsError(false);
			codeResponse.setUrl(flaskCleveredIndex);
			return codeResponse;
		}else if (codeRequest.getCode().contains("'home.html'")) {
			codeResponse.setIsError(false);
			codeResponse.setUrl(flaskCleveredHome);
			return codeResponse;
		}else if (codeRequest.getCode().contains("'first_home.html'")) {
			codeResponse.setIsError(false);
			codeResponse.setUrl(flaskCleveredFirstHome);
			return codeResponse;
		}else if (codeRequest.getCode().contains("'second_home.html'")) {
			codeResponse.setIsError(false);
			codeResponse.setUrl(flaskCleveredSecondHome);
			return codeResponse;
		}
		
		if (codeRequest.getCode().contains(".show()")) {
			drawGraph = true;
		}
		// replacing code .show() with savefig method to save the file
		String outputFileName = "cleaver100_" + UUID.randomUUID().toString() + ".png";
		codeRequest.setCode(codeRequest.getCode().replace(".show()", ".savefig('" + outputFileName + "')"));
		// Writing input into input.txt
		String codeOutput = "The is some problem at server while compiling code!";
		try {

        	Files.write(Paths.get("Main.py"), codeRequest.getCode().getBytes());
			codeOutput = crunProcess(pythonVersionCommand + " Main.py");
			if (codeOutput.contains("Main.py")) {
				codeResponse.setIsError(true);
				codeOutput = codeOutput.substring(codeOutput.indexOf("Main.py"));
				codeResponse.setStderr(codeOutput);

			} else {
				codeResponse.setIsError(false);
				codeResponse.setStdout(codeOutput);
			}
			//codeOutput = codeOutput.replaceAll("\n", "<br/>");
			//LOGGER.debug("__________Text Code output_______________ = " + codeOutput);

			if (drawGraph && codeResponse.getIsError()==false) {
				// Reading the generate image/graph
				byte[] graphs = Files.readAllBytes(Paths.get(outputFileName));
				String encodedString = Base64.getEncoder().encodeToString(graphs);
				codeResponse.setStdout(codeOutput+"chartstartb'"+encodedString+"'chartend");
			}
			
			codeResponse.setCombined(codeResponse.getStdout());

		} catch (Exception e) {
			//LOGGER.debug("Exception occurs while processing = " + e.getMessage());
			e.printStackTrace();
		} finally {
			FileUtils.deleteQuietly(new File(codeOutput));
		}
		
		if (!drawGraph) {
			//LOGGER.debug("_________________________");
			//LOGGER.info(codeResponse.toString());
			//LOGGER.debug("_________________________");
		}
		return codeResponse;
	}

	private static String printLines(InputStream ins) throws Exception {
		StringBuilder builder = new StringBuilder();
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			builder.append(line + "\n");
		}
		
		if(!Objects.isNull(in)) {
			in.close();
		}
		return builder.toString();
	}

}
