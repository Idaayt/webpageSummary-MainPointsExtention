package com.aditya.summaryapi;

public class ImpPointsService {

    public static String getImpPoints(String tabUrl) {
    	String urlcontent = ExtractPage.getContent(tabUrl);
    	//TextSummarizer ts = new TextSummarizer(urlcontent);
    	//return ts.getSentences(10);
    	SummaryTool summaryTool = new SummaryTool();
    	summaryTool.init(urlcontent);
    	summaryTool.extractSentenceFromContext();
    	summaryTool.groupSentencesIntoParagraphs();
    	summaryTool.createIntersectionMatrix();
    	summaryTool.createSummary();
    	return summaryTool.getMainPoints(10);
    }

}
