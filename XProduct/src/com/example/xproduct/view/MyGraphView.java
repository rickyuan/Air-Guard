package com.example.xproduct.view;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

public class MyGraphView {

	private Context context;
	private GraphicalView view;
	private String[] YStr = { "500", "400", "300", "200", "100", "50", "0" };

	private String[] XStr = { "0", "2", "4", "6", "8", "10", "12", "14", "16",
			"18", "20", "24" };
	private XYMultipleSeriesDataset mDataset;

	public MyGraphView(Context context) {
		this.context = context;
		lineView();
	}

	public View getView() {
		return view;
	}

	// ����ͼ
	public void lineView() {
		// ͬ������Ҫ����dataset����ͼ��Ⱦ��renderer

		fillData();
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		// ����ͼ���X��ĵ�ǰ����
		mRenderer
				.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
		// mRenderer.setXTitle("����");// ����ΪX��ı���
		// mRenderer.setYTitle("�۸�");// ����y��ı���
		mRenderer.setAxisTitleTextSize(20);// ����������ı���С
		// mRenderer.setChartTitle("�۸�����ͼ");// ����ͼ�����
		// mRenderer.setChartTitleTextSize(30);// ����ͼ��������ֵĴ�С
		// mRenderer.setLabelsTextSize(18);// ���ñ�ǩ�����ִ�С
		// mRenderer.setLegendTextSize(20);// ����ͼ���ı���С
		// mRenderer.setPointSize(10f);// ���õ�Ĵ�С
		mRenderer.setYAxisMin(0);// ����y����Сֵ��0
		mRenderer.setYAxisMax(15);
		mRenderer.setYLabels(10);// ����Y��̶ȸ�����ò�Ʋ�̫׼ȷ��
		mRenderer.setXAxisMax(12);
		// mRenderer.setShowGrid(true);// ��ʾ����
		// ��x��ǩ��Ŀ��ʾ�磺1,2,3,4�滻Ϊ��ʾ1�£�2�£�3�£�4��
		// mRenderer.addXTextLabel(1, "1��");
		// mRenderer.addXTextLabel(2, "2��");
		// mRenderer.addXTextLabel(3, "3��");
		// mRenderer.addXTextLabel(4, "4��");
		// mRenderer.setXLabels(0);// ����ֻ��ʾ��1�£�2�µ��滻��Ķ���������ʾ1,2,3��
		mRenderer.setMargins(new int[] { 20, 30, 15, 20 });// ������ͼλ��

		XYSeriesRenderer r = new XYSeriesRenderer();// (������һ���߶���)
		r.setColor(Color.BLUE);// ������ɫ
		r.setPointStyle(PointStyle.POINT);// ���õ����ʽ
		// r.setFillPoints(true);// ���㣨��ʾ�ĵ��ǿ��Ļ���ʵ�ģ�
		// r.setDisplayChartValues(true);// �����ֵ��ʾ����
		// r.setChartValuesSpacing(10);// ��ʾ�ĵ��ֵ��ͼ�ľ���
		r.setChartValuesTextSize(25);// ���ֵ�����ִ�С

		// r.setFillBelowLine(true);//�Ƿ��������ͼ���·�
		// r.setFillBelowLineColor(Color.GREEN);//������ɫ����������þ�Ĭ�����ߵ���ɫһ��
		r.setLineWidth(3);// �����߿�
		mRenderer.addSeriesRenderer(r);

		XYSeriesRenderer rTwo = new XYSeriesRenderer();// (������һ���߶���)
		rTwo.setColor(Color.GRAY);// ������ɫ
		rTwo.setPointStyle(PointStyle.POINT);// ���õ����ʽ
		// rTwo.setFillPoints(true);// ���㣨��ʾ�ĵ��ǿ��Ļ���ʵ�ģ�
		// rTwo.setDisplayChartValues(true);// �����ֵ��ʾ����
		// rTwo.setChartValuesSpacing(10);// ��ʾ�ĵ��ֵ��ͼ�ľ���
		rTwo.setChartValuesTextSize(25);// ���ֵ�����ִ�С

		// rTwo.setFillBelowLine(true);//�Ƿ��������ͼ���·�
		// rTwo.setFillBelowLineColor(Color.GREEN);//������ɫ����������þ�Ĭ�����ߵ���ɫһ��
		rTwo.setLineWidth(3);// �����߿�

		mRenderer.addSeriesRenderer(rTwo);

		view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
		view.setBackgroundColor(Color.TRANSPARENT);
	}

	private void fillData() {
		mDataset = new XYMultipleSeriesDataset();
		XYSeries series = new XYSeries("��һ����");
		mDataset.addSeries(series);
		XYSeries seriesTwo = new XYSeries("�ڶ�����");
		mDataset.addSeries(seriesTwo);
		for (int i = 0; i < 24; i++) {
			double pm = Math.random();
			pm *= 500;
			series.add(i, pm);
		}
		for (int i = 0; i < 24; i++) {
			double pm = Math.random();
			pm *= 500;
			seriesTwo.add(i, pm);
		}
	}
}
