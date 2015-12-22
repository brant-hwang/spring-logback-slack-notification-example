package io.brant.spring.logback.config;

import ch.qos.logback.classic.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "log")
public class LogConfig {

	private Level level;

	private Slack slack;

	private Database database;

	public static class Slack {
		private boolean enabled;
		private String webHookUrl;
		private String channel;

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public String getWebHookUrl() {
			return webHookUrl;
		}

		public void setWebHookUrl(String webHookUrl) {
			this.webHookUrl = webHookUrl;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}
	}

	public static class Database {
		private boolean enabled;

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Slack getSlack() {
		return slack;
	}

	public void setSlack(Slack slack) {
		this.slack = slack;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}
}
