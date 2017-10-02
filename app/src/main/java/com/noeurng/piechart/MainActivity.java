package com.noeurng.piechart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class MainActivity extends AppCompatActivity {

    public static final int[] MATERIAL_COLORS = {rgb("#20b1c3"), rgb("#6968b2"), rgb("#e6892e"), rgb("#202024"), rgb("#7d7d7f")};
    protected String[] mChartLabel = new String[]{"Label A", "Label B", "Label C", "Label D", "Label D"};
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = (PieChart) findViewById(R.id.linechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDrawEntryLabels(true); // disable label items

        // Disable Legend Chart View
        Legend l = pieChart.getLegend();
        l.setEnabled(true);

        // Hole View
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad); // Rotate Event
        // Start Rotation View
        pieChart.setRotationAngle(0);

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        List<Float> arrAmount = new ArrayList<>();
        // Add Fix 3 Items into Chart
        arrAmount.add(99000000f);
        arrAmount.add(60000000f);
        arrAmount.add(10000000f);
        setData(arrAmount);
    }

    /*
     * Set Chart Items Value
     *
     * @param amounts : ArrayList Int {#########, #########}
     */
    private void setData(List<Float> amounts) {
        try {
            ArrayList<PieEntry> entries = new ArrayList<>();

            for (int i = 0; i < amounts.size(); i++) {
                entries.add(new PieEntry(amounts.get(i),
                        mChartLabel[i % mChartLabel.length]));
            }

            PieDataSet dataSet = new PieDataSet(entries, "Quarterly Revenues 2016");

            dataSet.setDrawIcons(true);

            dataSet.setSliceSpace(3f);
            dataSet.setIconsOffset(new MPPointF(0, 40));
            dataSet.setSelectionShift(5f);

            // Disable Chart Value View
            dataSet.setDrawValues(true);

            // Add colors for Chart Items
            ArrayList<Integer> colors = new ArrayList<>();//
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

//        for (int c : ColorTemplate.MATERIAL_COLORS)
//            colors.add(c);

//            for (int c : MATERIAL_COLORS)
//                colors.add(c);

//        colors.add(ColorTemplate.getHoloBlue());

            dataSet.setColors(colors);

            PieData data = new PieData(dataSet);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(11f);
            data.setValueTextColor(Color.WHITE);
            pieChart.setData(data);

            // undo all highlights
            pieChart.highlightValues(null);

            pieChart.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
