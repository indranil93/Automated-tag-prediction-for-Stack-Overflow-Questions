
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Post_Test {

	public static void main(String[] args){

	      try {	
	         File inputFile = new File("Posts.xml");
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         UserHandler userhandler = new UserHandler();
	         saxParser.parse(inputFile, userhandler);     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }   
	}

	class UserHandler extends DefaultHandler {

	   boolean SiteInfo = false;
	   boolean Posts = false;
	   boolean Row = false;
	   
	   boolean SiteName = false;
	   boolean Base = false;
	   boolean Generator = false;
	   boolean Case = false;
	   boolean Namespaces = false;
	   boolean IndNamespace = false;
	   int val=0;
	   String RowId;
	   String Title;
	   String Tags;
	   List<String> StopWords =Arrays.asList("a", "able", "about", "above", "abroad", "according", "accordingly", "across", "actually", "adj", "after", "afterwards", "again", "against", "ago", "ahead", "ain't", "all", "allow", "allows", "almost", "alone", "along", "alongside", "already", "also", "although", "always", "am", "amid", "amidst", "among", "amongst", "amoungst", "amount", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "aren't", "around", "as", "a's", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "back", "backward", "backwards", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "begin", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "bill", "both", "bottom", "brief", "but", "by", "call", "came", "can", "cannot", "cant", "can't", "caption", "cause", "causes", "certain", "certainly", "changes", "clearly", "c'mon", "co", "co.", "com", "come", "comes", "computer", "con", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "couldn't", "course", "cry", "c's", "currently", "dare", "daren't", "de", "definitely", "describe", "described", "despite", "detail", "did", "didn't", "different", "directly", "do", "does", "doesn't", "doing", "done", "don't", "down", "downwards", "due", "during", "each", "edu", "eg", "eight", "eighty", "either", "eleven", "else", "elsewhere", "empty", "end", "ending", "enough", "entirely", "especially", "et", "etc", "even", "ever", "evermore", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "fairly", "far", "farther", "few", "fewer", "fifteen", "fifth", "fify", "fill", "find", "fire", "first", "five", "followed", "following", "follows", "for", "forever", "former", "formerly", "forth", "forty", "forward", "found", "four", "from", "front", "full", "further", "furthermore", "get", "gets", "getting", "give", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadn't", "half", "happens", "hardly", "has", "hasnt", "hasn't", "have", "haven't", "having", "he", "he'd", "he'll", "hello", "help", "hence", "her", "here", "hereafter", "hereby", "herein", "here's", "hereupon", "hers", "herse", "herself", "he's", "hi", "him", "himse", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "hundred", "i", "i'd", "ie", "if", "ignored", "i'll", "i'm", "immediate", "in", "inasmuch", "inc", "inc.", "indeed", "indicate", "indicated", "indicates", "inner", "inside", "insofar", "instead", "interest", "into", "inward", "is", "isn't", "it", "it'd", "it'll", "its", "it's", "itse", "itself", "i've", "just", "k", "keep", "keeps", "kept", "know", "known", "knows", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "let's", "like", "liked", "likely", "likewise", "little", "look", "looking", "looks", "low", "lower", "ltd", "made", "mainly", "make", "makes", "many", "may", "maybe", "mayn't", "me", "mean", "meantime", "meanwhile", "merely", "might", "mightn't", "mill", "mine", "minus", "miss", "more", "moreover", "most", "mostly", "move", "mr", "mrs", "much", "must", "mustn't", "my", "myse", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needn't", "needs", "neither", "never", "neverf", "neverless", "nevertheless", "new", "next", "nine", "ninety", "no", "nobody", "non", "none", "nonetheless", "noone", "no-one", "nor", "normally", "not", "nothing", "notwithstanding", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "one's", "only", "onto", "opposite", "or", "other", "others", "otherwise", "ought", "oughtn't", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "part", "particular", "particularly", "past", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provided", "provides", "put", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "recent", "recently", "regarding", "regardless", "regards", "relatively", "respectively", "right", "round", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somebody", "someday", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "system", "take", "taken", "taking", "tell", "ten", "tends", "th", "than", "thank", "thanks", "thanx", "that", "that'll", "thats", "that's", "that've", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "there'd", "therefore", "therein", "there'll", "there're", "theres", "there's", "thereupon", "there've", "these", "they", "they'd", "they'll", "they're", "they've", "thick", "thin", "thing", "things", "think", "third", "thirty", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "till", "to", "together", "too", "took", "top", "toward", "towards", "tried", "tries", "truly", "try", "trying", "t's", "twelve", "twenty", "twice", "two", "un", "under", "underneath", "undoing", "unfortunately", "unless", "unlike", "unlikely", "until", "unto", "up", "upon", "upwards", "us", "use", "used", "useful", "uses", "using", "usually", "v", "value", "various", "versus", "very", "via", "viz", "vs", "want", "wants", "was", "wasn't", "way", "we", "we'd", "welcome", "well", "we'll", "went", "were", "we're", "weren't", "we've", "what", "whatever", "what'll", "what's", "what've", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "where's", "whereupon", "wherever", "whether", "which", "whichever", "while", "whilst", "whither", "who", "who'd", "whoever", "whole", "who'll", "whom", "whomever", "who's", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wonder", "won't", "would", "wouldn't", "yes", "yet", "you", "you'd", "you'll", "your", "you're", "yours", "yourself", "yourselves", "you've", "zero", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v","w", "x", "y", "z", "ga", "lt", "link", "fa", "eu", "bs", "hr", "ref", "ref", "en", "em");
	   @Override
	   public void startElement(String uri, 
	      String localName, String qName, Attributes attributes)
	         throws SAXException {
		  if (qName.equalsIgnoreCase("posts")) {
		    	  Posts = true;
		  }  	  
		
	      else if (qName.equalsIgnoreCase("row")) {
	    	  Row = true;
	    	  		 RowId = attributes.getValue("Id");
	    	  		 System.out.println("RowId : " + RowId);
	    	         Title = attributes.getValue("Title");
	    	         if(Title==null)
	    	        	 System.out.println("Title Empty");
	    	         else
	    	        	 System.out.println("Title : " + Title);
	    	         
	    	         Tags = attributes.getValue("Tags");
	    	         if(Tags==null)
	    	        	 System.out.println("Tags Empty");
	    	         else
	    	        	 System.out.println("Tags : " + Tags);
	    	         
	    	  }
	     
	    	
	      
	   }

	   @Override
	   public void endElement(String uri, 
	      String localName, String qName) throws SAXException {
		   
	      if (qName.equalsIgnoreCase("row")) {
	         System.out.println("End Element :" + qName);
	         char chArray[];
			   StringBuilder sb_title = new StringBuilder(); 
			   
			   if(Title!=null)
			   {
				   //sb_title.append(Title);
				   //if(sb_title.length()>2)
				   	{
				         //chArray=toCharArray(sb_title);
				         ProcessTitle pt = new ProcessTitle();
				         //pt.FindWord(chArray);
					   //pt.FindWord(sb_title.toString());
				         pt.FindWord(Title);
				   	}
			   }
			   //StringBuilder sb_tag = new StringBuilder();
			   //sb_tag.append(Tags);
			   if(Tags!=null)
			   {	   
				   ProcessTag ptg=new ProcessTag();
				   ptg.FindTag(Tags);
			   }   
	      }
	      else if (qName.equalsIgnoreCase("posts")) {
		         System.out.println("End Element :" + qName);
		      }
	   }

	   @Override
	   public void characters(char ch[], 
	      int start, int length) throws SAXException {	 
		 
	     
	   }
	   public static char[] toCharArray(StringBuilder input)
		{
			int length = input.length();
			//System.out.println("Bodylength: inside tochar"+length);
			char charArray[] = new char[length];
			input.getChars(0, length, charArray, 0);
			return charArray; 
		}
}