package com.example.apprealcasamoneda.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.apprealcasamoneda.MainActivity
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FragmentCitaPreviaBinding
import com.example.apprealcasamoneda.databinding.FragmentObtenerCertificadoBinding
import com.example.apprealcasamoneda.fragments.PhysicalPersonCertificates.PhysicalPerson
import com.example.apprealcasamoneda.fragments.RepresentativeCertificates.Representative

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ObtenerCertificado.newInstance] factory method to
 * create an instance of this fragment.
 */
class ObtenerCertificado  : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentObtenerCertificadoBinding

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


        binding = FragmentObtenerCertificadoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.iconPhysicalDropdown?.setOnClickListener{
            showHide(binding.RLPhysicalPersonDropdown, binding.iconPhysicalDropdown)
        }

        binding.iconRepresentateDropdown?.setOnClickListener{
            showHide(binding.RLRepresentationDropdown, binding.iconRepresentateDropdown)
        }


        binding.iconPublicAdministrationDropdown?.setOnClickListener{
            showHide(binding.RLPublicAdministrationDropdown, binding.iconPublicAdministrationDropdown)
        }


        binding.iconComponentCertificatesDropdown?.setOnClickListener{
            showHide(binding.RLComponentCertificatesDropdown, binding.iconComponentCertificatesDropdown)
        }

        binding.iconGoToRepresentation.setOnClickListener{
            val transition = fragmentManager?.beginTransaction()
            val representative = Representative()
            transition?.replace(R.id.mainContainer, representative)
            transition?.addToBackStack(null)
            transition?.commit()
        }


        binding.rectangularLYPhysicalPerson?.setOnClickListener{
            val transition = fragmentManager?.beginTransaction()
            val physicalPerson = PhysicalPerson()
            transition?.replace(R.id.mainContainer, physicalPerson)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        return binding.root
    }

    private fun showHide(relativeLayout: RelativeLayout?, imageView: ImageView) {
        val fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        //val fadeOutAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)

        if (relativeLayout != null) {
            if (relativeLayout.visibility == View.GONE ){

                relativeLayout.startAnimation(fadeInAnimation)
                relativeLayout.visibility = View.VISIBLE
                imageView.rotation = 90f


            }else{

                //textView.startAnimation(fadeInAnimation)
                relativeLayout.visibility = View.GONE
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
         * @return A new instance of fragment ObtenerCertificado.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ObtenerCertificado().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}