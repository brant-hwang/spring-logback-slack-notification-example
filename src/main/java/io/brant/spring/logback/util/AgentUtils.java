package io.brant.spring.logback.util;

import eu.bitwalker.useragentutils.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AgentUtils {

	public static String getUserAgentString(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	public static String getUserAgentString() {
		return getUserAgentString(HttpUtils.getCurrentRequest());
	}

	public static UserAgent getUserAgent(HttpServletRequest request) {
		try {
			String userAgentString = getUserAgentString(request);
			return UserAgent.parseUserAgentString(userAgentString);
		} catch (Exception e) {
			// ignored
		}
		return null;
	}

	public static UserAgent getUserAgent() {
		return getUserAgent(HttpUtils.getCurrentRequest());
	}

	public static OperatingSystem getUserOs(HttpServletRequest request) {
		UserAgent userAgent = getUserAgent(request);
		return userAgent == null ? OperatingSystem.UNKNOWN : userAgent.getOperatingSystem();
	}

	public static OperatingSystem getUserOs() {
		return getUserOs(HttpUtils.getCurrentRequest());
	}

	public static Browser getBrowser(HttpServletRequest request) {
		UserAgent userAgent = getUserAgent(request);
		return userAgent == null ? Browser.UNKNOWN : userAgent.getBrowser();
	}

	public static Browser getBrowser() {
		return getBrowser(HttpUtils.getCurrentRequest());
	}

	public static Version getBrowserVersion(HttpServletRequest request) {
		UserAgent userAgent = getUserAgent(request);
		return userAgent == null ? new Version("0", "0", "0") : userAgent.getBrowserVersion();
	}

	public static BrowserType getBrowserType(HttpServletRequest request) {
		Browser browser = getBrowser(request);
		return browser == null ? BrowserType.UNKNOWN : browser.getBrowserType();
	}

	public static BrowserType getBrowserType() {
		return getBrowserType(HttpUtils.getCurrentRequest());
	}

	public static RenderingEngine getRenderingEngine(HttpServletRequest request) {
		Browser browser = getBrowser(request);
		return browser == null ? RenderingEngine.OTHER : browser.getRenderingEngine();
	}

	public static RenderingEngine getRenderingEngine() {
		return getRenderingEngine(HttpUtils.getCurrentRequest());
	}

	public static Version getBrowserVersion() {
		return getBrowserVersion(HttpUtils.getCurrentRequest());
	}

	public static DeviceType getDeviceType(HttpServletRequest request) {
		OperatingSystem operatingSystem = getUserOs(request);
		return operatingSystem == null ? DeviceType.UNKNOWN : operatingSystem.getDeviceType();
	}

	public static DeviceType getDeviceType() {
		return getDeviceType(HttpUtils.getCurrentRequest());
	}

	public static Manufacturer getManufacturer(HttpServletRequest request) {
		OperatingSystem operatingSystem = getUserOs(request);
		return operatingSystem == null ? Manufacturer.OTHER : operatingSystem.getManufacturer();
	}

	public static Manufacturer getManufacturer() {
		return getManufacturer(HttpUtils.getCurrentRequest());
	}

	public static Map<String, String> getAgentDetail(HttpServletRequest request) {
		Map<String, String> agentDetail = new HashMap<>();
		agentDetail.put("browser", getBrowser(request).toString());
		agentDetail.put("browserType", getBrowserType(request).toString());
		agentDetail.put("browserVersion", getBrowserVersion(request).toString());
		agentDetail.put("renderingEngine", getRenderingEngine(request).toString());
		agentDetail.put("os", getUserOs(request).toString());
		agentDetail.put("deviceType", getDeviceType(request).toString());
		agentDetail.put("manufacturer", getManufacturer(request).toString());

		return agentDetail;
	}
}
