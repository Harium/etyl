package com.harium.etyl.i18n;

/**
 * 
 * @author yuripourre
 *
 */

public enum Language {
	
	ARABIC_EGYPT ("ar_EG", "UTF-8", true),
	ARABIC_ISRAEL ("ar_IL", "UTF-8", true),
	BULGARIAN_BULGARIA ("bg_BG", "UTF-8", false),
	CATALAN_SPAIN ("ca_ES", "UTF-8", false),
	CROATIAN_CROATIA ("hr_HR", "UTF-8", false),
	DANISH_DENMARK ("da_DK", "UTF-8", false),
	CHINESE_PRC ("zh_CN", "UTF-8", false),
	CHINESE_TAIWAN("zh_TW", "UTF-8", false),
	CZECH("cs_CZ", "UTF-8", false),
	DUTCH_BELGIUM("nl_BE", "UTF-8", false),
	DUTCH_NETHERLANDS ("nl_NL", "UTF-8", false),
	ENGLISH_AUSTRALIA("en_AU", "UTF-8", false),
	ENGLISH_US("en_US", "UTF-8", false),
	ENGLISH_BRITAIN ("en_GB", "UTF-8", false),
	ENGLISH_CANADA ("en_CA", "UTF-8", false),
	ENGLISH_INDIA ("en_IN", "UTF-8", false),
	ENGLISH_IRELAND ("en_IE", "UTF-8", false),
	ENGLISH_NEW_ZEALAND ("en_NZ", "UTF-8", false),
	ENGLISH_SINGAPORE ("en_SG", "UTF-8", false),
	ENGLISH_ZIMBABWE ("en_ZA", "UTF-8", false),
	FINNISH_FINLAND ("fi_FI", "UTF-8", false),
	FRENCH("fr_FR", "UTF-8", false),
	FRENCH_BELGIUM ("fr_BE", "UTF-8", false),
	FRENCH_CANADA ("fr_CA", "UTF-8", false),
	FRENCH_FRANCE ("fr_FR", "UTF-8", false),
	FRENCH_SWITZERLAND ("fr_CH", "UTF-8", false),
	GERMAN_AUSTRIA ("de_AT", "UTF-8", false),
	GERMAN_GERMANY("de_DE", "UTF-8", false),
	GERMAN_LIECHTENSTEIN("de_LI", "UTF-8", false),
	GERMAN_SWITZERLAND ("de_CH", "UTF-8", false),
	GREEK ("el_GR", "UTF-8", false),
	HEBREW_ISRAEL ("iw_IL", "UTF-8", true),
	HINDI_INDIA ("hi_IN", "UTF-8", false),
	HUNGARIAN ("hu_HU", "UTF-8", false),
	INDONESIAN ("in_ID", "UTF-8", false),
	ITALIAN_ITALY ("it_IT", "UTF-8", false),
	ITALIAN_SWITZERLAND  ("it_CH", "UTF-8", false),
	JAPANESE("ja_JP", "UTF-8", false),
	KOREAN ("ko_KR", "UTF-8", false),
	LATVIAN ("lv_LV", "UTF-8", false),
	LITHUANIAN ("lt_LT", "UTF-8", false),
	NORWEGIAN_NORWAY ("nb_NO", "UTF-8", false),
	POLISH ("pl_PL", "UTF-8", false),
	PORTUGUESE_BRAZIL ("pt_BR", "UTF-8", false),
	PORTUGUESE_PORTUGAL ("pt_PT", "UTF-8", false),
	ROMANIAN ("ro_RO", "UTF-8", false),
	RUSSIAN ("ru_RU", "UTF-8", false),
	SPANISH ("es_ES", "UTF-8", false),
	SERBIAN ("sr_RS", "UTF-8", false),
	SLOVAK ("sk_SK", "UTF-8", false),
	SLOVENIAN ("sl_SI", "UTF-8", false),
	SPANISH_US ("es_US", "UTF-8", false),
	SWEDISH ("sv_SE", "UTF-8", false),
	TAGALOG_PHILIPPINES ("tl_PH", "UTF-8", false),
	THAI ("th_TH", "UTF-8", false),
	TURKISH ("tr_TR", "UTF-8", false),
	UKRANIAN_UKRAINE ("uk_UA", "UTF-8", false),
	VIETNAMESE ("vi_VN", "UTF-8", false),
	
	UNKNOWN ("?", "UTF-8", false);
	
	private final String charsetName;
	private final String charsetEncode;
	private final boolean rtl; //right-to-left
	
	Language(String charsetName, String charsetEncode, boolean rtl) {
		this.charsetName = charsetName;
		this.charsetEncode = charsetEncode;
		this.rtl = rtl;
	}
	
	public final String getCharsetName(){ 
		return charsetName;
	}
	
	public final String getCharsetEncode(){ 
		return charsetEncode;
	}
	
	public final boolean isRTL(){ 
		return rtl;
	}

	public static Language byCode(String code) {
		String lowerCode = code.toLowerCase();
		for(Language language : values()) {
			if (language.charsetName.toLowerCase().equals(lowerCode)) {
				return language;
			}
		}
		return UNKNOWN;
	}
}
