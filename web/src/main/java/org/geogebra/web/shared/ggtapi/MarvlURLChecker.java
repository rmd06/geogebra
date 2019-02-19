package org.geogebra.web.shared.ggtapi;

import org.geogebra.common.move.ggtapi.operations.URLChecker;
import org.geogebra.common.move.ggtapi.operations.URLStatus;
import org.geogebra.common.util.AsyncOperation;

public class MarvlURLChecker implements URLChecker {

	@Override
	public void check(String url, AsyncOperation<URLStatus> callback) {
		if (!isUrl(url)) {
			callback.callback(new URLStatus("InvalidInput"));
		}
	}

	private native boolean isUrl(String url) /*-{
		var pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
				  '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.?)+[a-z]{2,}|'+ // domain name
				  '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
				  '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
				  '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
				  '(\\#[-a-z\\d_]*)?$','i'); // fragment locator
	  	return pattern.test(url);
	}-*/;
}