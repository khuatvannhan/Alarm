package com.nhankv.alarm

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhankv.addalarm.AddAlarmActivity
import com.nhankv.alarm.databinding.FragmentAlarmBinding
import com.nhankv.data.alarm.AlarmsRepository
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
class AlarmsFragment : Fragment(), AlarmListener {
    private val TAG = javaClass.name
    // TODO: Rename and change types of parameters
    private lateinit var viewDataBinding: FragmentAlarmBinding
    private lateinit var listAdapter: AlarmsAdapter
    private lateinit var mAlarmsRepository: AlarmsRepository
    private lateinit var mAlarmViewMode: AlarmsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, " ------- onCreateView ------")
        init()
        initDataBinding(inflater, container)
        // Inflate the layout for this fragment
        return viewDataBinding.root
    }

    private fun init() {
        if (!::mAlarmsRepository.isInitialized) {
            mAlarmsRepository = AlarmsRepository.getInstance()
        }
        if (!::mAlarmViewMode.isInitialized && activity != null) {
            mAlarmViewMode = AlarmsViewModel(activity!!.application, mAlarmsRepository)
        }
    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        viewDataBinding = FragmentAlarmBinding.inflate(inflater, container, false)
        viewDataBinding.viewModel = mAlarmViewMode
        viewDataBinding.listener = this
    }

    override fun selectAll() {

    }

    override fun deleteAlarmsSelected() {

    }

    override fun startDetailAlarm() {
        startActivity(Intent(activity, AddAlarmActivity::class.java))
    }

    companion object {
        @JvmStatic
        fun newInstance() = AlarmsFragment()
    }
}
