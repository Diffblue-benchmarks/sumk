package org.yx.conf;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.yx.util.CollectionUtil;

public class NamePairs {

	public static final String LN = "\n";

	private final Map<String, String> map;

	private final String data;

	public NamePairs(final String data) {
		this.data = data;
		this.map = data == null ? Collections.emptyMap() : CollectionUtil.loadMapFromText(data, LN, "=");
	}

	public NamePairs(final Map<String, String> map) {
		this.map = map;
		this.data = map == null ? "" : CollectionUtil.saveMapToText(map, LN, "=");
	}

	private NamePairs(Map<String, String> map, String data) {
		this.map = map;
		this.data = data;
	}

	public String getRawData() {
		return data;
	}

	public Map<String, String> values() {
		return Collections.unmodifiableMap(this.map);
	}

	public NamePairs unmodify() {
		return new NamePairs(Collections.unmodifiableMap(this.map), this.data);
	}

	public String getValue(String key) {
		if (key == null) {
			return null;
		}
		return map.get(key);
	}

	@Override
	public String toString() {
		return this.data;
	}

	public Collection<String> keys() {
		return this.map.keySet();
	}

	public InputStream toInputStream() {
		if (this.data == null || this.data.isEmpty()) {
			return new ByteArrayInputStream(new byte[0]);
		}
		return new ByteArrayInputStream(this.data.getBytes(AppInfo.systemCharset()));
	}

	public byte[] toBytes() {
		if (this.data == null || this.data.isEmpty()) {
			return new byte[0];
		}
		return this.data.getBytes(AppInfo.systemCharset());
	}

	public static NamePairs createByString(String data) {
		return new NamePairs(data);
	}

	public static NamePairs createByMap(Map<String, String> map) {
		return new NamePairs(map);
	}
}