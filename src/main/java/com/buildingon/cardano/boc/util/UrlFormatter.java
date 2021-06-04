package com.buildingon.cardano.boc.util;

import org.springframework.stereotype.Component;

@Component
public class UrlFormatter {

	public String formatUrlWithHttps(String url) {

		if (url != null && !url.isEmpty()) {
			if (url.startsWith("https:")) {
				return url;
			} else if (url.startsWith("http:")) {
				url = url.replace("http:", "https:");
				return url;
			} else {
				return "https://" + url;
			}
		}
		return url;
	}

}
