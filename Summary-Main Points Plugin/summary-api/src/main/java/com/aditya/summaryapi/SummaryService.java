package com.aditya.summaryapi;



public class SummaryService {

    public static String getSummary(String tabUrl) {
    	String urlcontent = ExtractPage.getContent(tabUrl);
    	//TextSummarizer ts = new TextSummarizer(urlcontent);
    	//return ts.summarize(100);
    	SummaryTool summaryTool = new SummaryTool();
    	summaryTool.init(urlcontent);
    	summaryTool.extractSentenceFromContext();
    	summaryTool.groupSentencesIntoParagraphs();
    	summaryTool.createIntersectionMatrix();
    	summaryTool.createSummary();
    	summaryTool.getSummary();
    	String summary = summaryTool.out;
    	return summary;
    }

}


