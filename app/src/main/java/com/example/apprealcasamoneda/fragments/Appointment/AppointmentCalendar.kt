package com.example.apprealcasamoneda.fragments.Appointment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FrAppointmentCalendarBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AppointmentCalendar.newInstance] factory method to
 * create an instance of this fragment.
 */
class AppointmentCalendar : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FrAppointmentCalendarBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrAppointmentCalendarBinding.inflate(inflater, container, false)

        binding.rectangularLYContinue.setOnClickListener{
            val appointmentForm = AppointmentForm()
            val transition = fragmentManager?.beginTransaction()

            val bundle = Bundle()
            bundle.putString("Appo", binding.AppoCalendarTxt.text.toString())
            bundle.putString("Appo2", binding.AppoCalendarTxt2.text.toString())
            appointmentForm.arguments = bundle

            transition?.replace(R.id.mainContainer, appointmentForm)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        val text1 = arguments?.getString("Appo")
        val text2 = arguments?.getString("Appo2")

        if (text1 == "Select") {
            binding.AppoCalendarTxt.text = "No ha seleccionado nada"
        }else{
            binding.AppoCalendarTxt.text = text1
        }

        if (text2 == "Select") {
            binding.AppoCalendarTxt2.text = "No ha seleccionado nada"
        } else {
            binding.AppoCalendarTxt2.text = text2

        }


        binding.oappointmentCalendarBackCross.setOnClickListener{
            val fragmentManager = requireFragmentManager()
            fragmentManager.popBackStack()
        }

        return binding.root
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AppointmentCalendar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AppointmentCalendar().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}