import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;

logger = LogFactory.getLog("hdfsscript.sample");

logger.info(binding.variables.keySet())

fsh.ls("/xd").each() {
	logger.info "${it.path.name} ..."
}
