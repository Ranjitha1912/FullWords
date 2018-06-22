package allconstants;


public class FullWordsConstants
{
	public enum FullConstants 
	{
		CLIENT_ID("29354-3021c25a4eaca49246dc50e262215393"),
		CLIENT_SECRET("d3OpCPkxaWEBfX2wr3Z7VPQkBUPh6Wxp0eVPKhjS"),
		REDIRECT_DEVELOPMENT("http://localhost:8080/authCallback"),
		REDIRECT_PRODUCTION("https://full-words-web.appspot.com/authCallback"),
		AW_API("https://api.anywhereworks.com/api/v1/user/me"),
		FULL_WORDS_API("https://full-learn.appspot.com/api/v1/words");
		
		String value;
		
		private FullConstants (String value)
		{
			this.value=value;
		}
		
		public String getValue()
		{
			return value;
		}
	};
}