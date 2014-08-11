package com.example.xproduct.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.hp.box.xproduct.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.GraphViewStyle;
import com.jjoe64.graphview.LineGraphView;

@SuppressLint("ResourceAsColor")
public class SimpleGraph {

	private GraphView graphView;
	private String[] YStr = { "500", "400", "300", "200", "100", "50", "0" };

	private String[] XStr = { "0", "2", "4", "6", "8", "10", "12", "14", "16",
			"18", "20", "24" };

	private GraphViewData[] gvData1;
	private GraphViewData[] gvData2;

	public SimpleGraph(Context context) {
		initView(context);
	}

	public View getView() {
		return graphView;
	}

	private void initView(Context context) {
		fillData();
		GraphViewSeries exampleSeries1 = new GraphViewSeries(gvData1);
		GraphViewSeries exampleSeries2 = new GraphViewSeries(gvData2);
		graphView = new LineGraphView(context);
		((LineGraphView) graphView).setLineBlack();
		graphView.setHorizontalLabels(XStr);
		graphView.setVerticalLeftLabels(YStr);
		graphView.setVerticalRightLabels(YStr);
		graphView.setTitleLeft("PM");
		graphView.setTitleRight("¼×È©");
		graphView.setGraphWidthPers(0.75);
		new GraphViewSeriesStyle(R.color.blue, 0);
		graphView.addSeries(exampleSeries1); // data
		new GraphViewStyle(Color.BLACK, Color.BLACK);
		new GraphViewSeriesStyle(R.color.orange, 0);
		graphView.addSeries(exampleSeries2); // data
		new GraphViewStyle(Color.BLACK, Color.BLACK);
	}

	private void fillData() {
		gvData1 = new GraphViewData[24];
		gvData2 = new GraphViewData[24];
		double pm1 = 20;
		for (int i = 0; i < 24; i++) {
			pm1 += 20;
			gvData1[i] = new GraphViewData(i, pm1);
		}
		for (int i = 0; i < 24; i++) {
			double pm = Math.random();
			pm *= 500;
			gvData2[i] = new GraphViewData(i, pm);
		}
	}
}