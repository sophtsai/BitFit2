package com.codepath.bitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val sql = AppDatabase.getInstance(view.context).exerciseDao()
        var avgDur = view.findViewById<TextView>(R.id.avg_dur_result)
        var longDur = view.findViewById<TextView>(R.id.longest_dur_result)
        var shortDur = view.findViewById<TextView>(R.id.shortest_dur_result)
        var avgCal = view.findViewById<TextView>(R.id.avg_cal_result)
        var leastCal = view.findViewById<TextView>(R.id.least_cal_result)
        var mostCal = view.findViewById<TextView>(R.id.most_cal_result)

        lifecycleScope.launch(Dispatchers.IO){
            avgDur.text = sql.avgDur().toString() + " min"
            shortDur.text = sql.shortDur().toString() + " min"
            longDur.text =sql.longDur().toString() + " min"

            avgCal.text = sql.avgCal().toString() + " cal"
            leastCal.text = sql.leastCal().toString() + " cal"
            mostCal.text =sql.mostCal().toString() + " cal"
        }

        return view
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}