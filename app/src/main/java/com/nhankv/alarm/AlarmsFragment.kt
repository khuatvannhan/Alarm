package com.nhankv.alarm

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhankv.alarm.databinding.FragmentAlarmBinding
import com.nhankv.data.alarm.model.Alarm
import com.nhankv.main.MainActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AlarmsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AlarmsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AlarmsFragment : Fragment() {
    private val TAG = javaClass.name
    // TODO: Rename and change types of parameters
    private lateinit var viewDataBinding: FragmentAlarmBinding
    private lateinit var listAdapter: AlarmsAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, " ------- onCreateView ------")
        viewDataBinding = FragmentAlarmBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as MainActivity).View
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() = AlarmsFragment()
    }
}
