package com.br.irecovery.util;

public class JWMICustom extends JWMI {

	static public String getNaWMI(final String classe, final String campo) {
		String wmi = "";

		try {
			wmi = JWMI.getWMIValue("select * from " + classe, campo);
		} catch (final Exception e) {
			wmi = "ERROR";
			e.printStackTrace();
		}

		return wmi;
	}

	static public String getArch() {
		final String arch = getNaWMI("Win32_OperatingSystem", "OSArchitecture");

		if (arch.contains("64"))
			return "x64";
		if (arch.contains("32"))
			return "";
		return null;
	}
}
