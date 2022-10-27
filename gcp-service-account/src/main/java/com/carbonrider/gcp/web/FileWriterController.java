/**
 * 
 */
package com.carbonrider.gcp.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;




@RestController
public class FileWriterController {

	@Autowired
	private Storage storage;
	
	@Value("${file.storage}")
	private Resource localFilePath;


	@GetMapping("/write-file/{fileName}")
	public WriteMessage writeFileToBucket(@PathVariable(name="fileName")String fileName)throws Exception{
		   BlobId blobId= BlobId.of("zap-bucket",fileName);
		   BlobInfo blobInfo= BlobInfo.newBuilder(blobId).build();
		   File fileToRead = new File(localFilePath.getFile(),fileName);
		   byte[] data= Files.readAllBytes(Paths.get(fileToRead.toURI()));
		   storage.create(blobInfo,data);

		   WriteMessage message =new WriteMessage();
		   message.setContents(new String(data));
		   return message;
		}
}
 class WriteMessage {
	private String contents;

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
