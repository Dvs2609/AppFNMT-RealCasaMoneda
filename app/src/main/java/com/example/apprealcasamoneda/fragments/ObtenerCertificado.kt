package com.example.apprealcasamoneda.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import androidx.fragment.app.Fragment
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
            binding.txtPhysicalPersonDropdown?.let { it1 ->
                showHide(binding.RLPhysicalPersonDropdown,
                    it1, binding.iconPhysicalDropdown!!
                )
            }
        }

        binding.iconRepresentateDropdown?.setOnClickListener{
            binding.txtRepresentationDropdown?.let { it2 ->
                showHide(binding.RLRepresentationDropdown,
                    it2, binding.iconRepresentateDropdown!!
                )
            }
        }

        binding.iconPublicAdministrationDropdown?.setOnClickListener{
            binding.txtPublicAdministrationDropdown?.let { it3 ->
                showHide(binding.RLPublicAdministrationDropdown,
                    it3, binding.iconPublicAdministrationDropdown!!
                )
            }

        }

        binding.iconComponentCertificatesDropdown?.setOnClickListener{
            binding.txtComponentCertificatesDropdown?.let { it4 ->
                showHide(binding.RLComponentCertificatesDropdown,
                    it4, binding.iconComponentCertificatesDropdown!!
                )
            }
        }


        class  NewFragment : Fragment() {

        }

        binding.rectangularLYPhysicalPerson?.setOnClickListener{

            val transition = fragmentManager?.beginTransaction()
            val newFr = NewFragment()
            transition?.replace(R.id.scrollviewPhysicalPerson, newFr)?.commit()
        }
        return binding.root
    }

    private fun showHide(relativeLayout: RelativeLayout?, textView: TextView, imageView: ImageView) {
        val fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        //val fadeOutAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)

        if (relativeLayout != null) {
            if (relativeLayout.visibility == View.GONE ){

                relativeLayout.startAnimation(fadeInAnimation)
                relativeLayout.visibility = View.VISIBLE
                textView.visibility = View.VISIBLE
                imageView.rotation = 90f


            }else{

                //textView.startAnimation(fadeInAnimation)
                relativeLayout.visibility = View.GONE
                textView.visibility = View.GONE
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