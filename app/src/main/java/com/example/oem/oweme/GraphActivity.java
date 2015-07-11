package com.example.oem.oweme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;

import java.util.ArrayList;
import java.util.Map;



public class GraphActivity extends Activity implements OnChartValueSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        this.getActionBar().hide();


        Intent receivedIntent = getIntent();

        String contactName = receivedIntent.getStringExtra("name");
        String amounts = receivedIntent.getStringExtra("amountList");
        String[] amountList = amounts.split(",");

        LineChart lineChart = (LineChart) findViewById(R.id.chart);
        setUpLineChart(lineChart);


        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        Integer i = 0;
        for(String amount : amountList){

            int amountValue = Integer.parseInt(amount);
            Entry entry = new Entry(amountValue, i);
            entries.add(entry);

            xVals.add(i.toString());

            i++;
        }

        LineDataSet lineDataSet = new LineDataSet(entries, contactName);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.addColor(R.color.colorPrimary);
        lineDataSet.setLineWidth(6);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(xVals, dataSets);
        lineChart.setDescription(contactName);
        lineChart.setData(data);
        lineChart.animateXY(1500, 1500, Easing.EasingOption.EaseInExpo, Easing.EasingOption.EaseInBack);
        lineChart.invalidate();
    }

    private void setUpLineChart(LineChart lineChart) {
        lineChart.setTouchEnabled(true);
        lineChart.setOnChartValueSelectedListener(this);
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawMarkerViews(false);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        YAxis yAxisRight = lineChart.getAxisRight();

        yAxisLeft.setAxisMinValue(-5);
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setAxisLineWidth(4);
        yAxisLeft.setStartAtZero(false);
        yAxisLeft.setAxisLineColor(Color.BLACK);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(false);

        LimitLine ll = new LimitLine(0, "");
        ll.setLineColor(Color.BLACK);
        ll.setLineWidth(4);

        yAxisLeft.addLimitLine(ll);
        yAxisLeft.setDrawLimitLinesBehindData(true);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
    }


    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
