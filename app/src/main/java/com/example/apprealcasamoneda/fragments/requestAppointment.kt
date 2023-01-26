package com.example.apprealcasamoneda.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.ScrollView
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FrAppointmentBinding
import com.example.apprealcasamoneda.fragments.Appointment.AppointmentCalendar
import com.example.apprealcasamoneda.fragments.Appointment.AppointmentForm

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [requestAppointment.newInstance] factory method to
 * create an instance of this fragment.
 */
class requestAppointment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FrAppointmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrAppointmentBinding.inflate(inflater, container, false)

        binding.dropdownSelectionicon.setOnClickListener{
            hide(binding.scrollSelection2, binding.dropdownSelectionicon2)
            showHide(binding.scrollSelection, binding.dropdownSelectionicon)
        }
        binding.dropdownSelectionicon2.setOnClickListener{
            hide(binding.scrollSelection, binding.dropdownSelectionicon)
            showHide(binding.scrollSelection2, binding.dropdownSelectionicon2)
        }

        binding.rectangularLYContinue.setOnClickListener{
            val appointmentCalendar = AppointmentCalendar()
            val appointmentForm = AppointmentForm()
            val transition = fragmentManager?.beginTransaction()

            val bundle = Bundle()
            bundle.putString("Appo", binding.Dropdown1SelectTxt.text.toString())
            bundle.putString("Appo2", binding.Dropdown2SelectTxt.text.toString())
            appointmentCalendar.arguments = bundle

            transition?.replace(R.id.mainContainer, appointmentCalendar)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        //Dropdown1
        binding.dropdown1Txt.setOnClickListener {
            binding.Dropdown1SelectTxt.text = binding.dropdown1Txt.text
            hide(binding.scrollSelection, binding.dropdownSelectionicon)
        }
        binding.dropdown1Txt2.setOnClickListener {
            binding.Dropdown1SelectTxt.text = binding.dropdown1Txt2.text
            hide(binding.scrollSelection, binding.dropdownSelectionicon)
        }
        binding.dropdown1Txt3.setOnClickListener {
            binding.Dropdown1SelectTxt.text = binding.dropdown1Txt3.text
            hide(binding.scrollSelection, binding.dropdownSelectionicon)
        }
        binding.dropdown1Txt4.setOnClickListener {
            binding.Dropdown1SelectTxt.text = binding.dropdown1Txt4.text
            hide(binding.scrollSelection, binding.dropdownSelectionicon)
        }

        //Dropdown2
        binding.dropdown2Txt.setOnClickListener {
            binding.Dropdown2SelectTxt.text = binding.dropdown2Txt.text
            hide(binding.scrollSelection2, binding.dropdownSelectionicon)
        }
        binding.dropdown2Txt2.setOnClickListener {
            binding.Dropdown2SelectTxt.text = binding.dropdown2Txt2.text
            hide(binding.scrollSelection2, binding.dropdownSelectionicon)
        }
        binding.dropdown2Txt3.setOnClickListener {
            binding.Dropdown2SelectTxt.text = binding.dropdown2Txt3.text
            hide(binding.scrollSelection2, binding.dropdownSelectionicon)
        }
        binding.dropdown2Txt4.setOnClickListener {
            binding.Dropdown2SelectTxt.text = binding.dropdown2Txt4.text
            hide(binding.scrollSelection2, binding.dropdownSelectionicon)
        }
        return binding.root
    }

    private fun hide(scrollView: ScrollView?, imageView: ImageView) {
        val fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        //val fadeOutAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)

        if (scrollView != null) {
            if (scrollView.visibility == View.VISIBLE ){

                scrollView.startAnimation(fadeInAnimation)
                scrollView.visibility = View.GONE
                imageView.rotation = 0f

            }
        }
    }

    private fun showHide(scrollView: ScrollView?, imageView: ImageView) {
        val fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        //val fadeOutAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)

        if (scrollView != null) {
            if (scrollView.visibility == View.GONE ){

                scrollView.startAnimation(fadeInAnimation)
                scrollView.visibility = View.VISIBLE
                imageView.rotation = 90f


            }else{

                //textView.startAnimation(fadeInAnimation)
                scrollView.visibility = View.GONE
                imageView.rotation = 0f
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CitaPrevia.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            requestAppointment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}