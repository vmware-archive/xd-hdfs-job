import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;

logger = LogFactory.getLog("hdfsscript.archive");
sdf = new SimpleDateFormat(dateFormat);

if (!binding.variables.containsKey("appendDate")) appendDate = false;
if (!binding.variables.containsKey("deleteFiles")) deleteFiles = false;

if (!binding.variables.containsKey("sourceDir"))  {
	logger.warn "Missing sourceDir property"
	return
}

if (!binding.variables.containsKey("targetDir"))  {
	logger.warn "Missing targetDir property"
	return
}

logger.debug "sourceDir is ${sourceDir}"
logger.debug "date format is ${dateFormat}"
logger.debug "deleteFiles ${deleteFiles}"

boolean bAppendDate = appendDate == "true" ? true : false
boolean bDeleteFiles = deleteFiles == "true" ? true : false

if (!dateFormat) {
	dateFormat = "yyyy-MM-dd"
} else {
	bAppendDate = true\
}

if (!fsh.test(targetDir)) {
	logger.info "${targetDir} doesn't exist, creating..."
	fsh.mkdir(targetDir)
}

fsh.ls(sourceDir).each() {

	if (it.directory) {
		return;
	}
	
	if (it.path.name == "dummy.csv") {
		return;
	}

	date = sdf.format(new Date(it.modificationTime))

	src = it.path.toString()
	dst = "${targetDir}/${it.path.name}"
	if (bAppendDate) {
		dst = "${dst}_${date}"
	}
	
	if (fsh.test(dst)) {
		logger.info "${dst} already exists, skipping..."
		return;
	}

	logger.debug "${src}"
	logger.debug "${dst}"
	logger.debug "---------------"

	if (bDeleteFiles) {
		fsh.mv(src, dst);
	} else {
		fsh.cp(src, dst);
	}
}
