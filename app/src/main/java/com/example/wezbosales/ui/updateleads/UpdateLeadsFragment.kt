package com.example.wezbosales.ui.updateleads

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import androidx.navigation.fragment.navArgs
import com.example.wezbosales.R
import com.example.wezbosales.databinding.FragmentUpdateLeadsBinding

class UpdateLeadsFragment : Fragment() {

    lateinit var binding:FragmentUpdateLeadsBinding

    //get values from complete or pending or enquiry leads
    val args:UpdateLeadsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding=FragmentUpdateLeadsBinding.inflate(inflater, container, false)

        //values get from complete or pending or enquiry leads
        var leaadtype=args.leadtype
        var id=args.id


        //drop down menu for select one plan
        val plane=resources.getStringArray(R.array.plan)                     //drop down menu for select plane
        val arrayAdapter= ArrayAdapter(requireContext(), R.layout.textview_for_dorpdown_menu,plane)
        binding.plane1.setAdapter(arrayAdapter)

        //address values
        val ar=resources.getStringArray(R.array.address)
        val adapter= ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,ar)
        binding.address1.setAdapter(adapter)
        binding.address1.threshold=1
        binding.address1.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        //drop down menu for business type
        val businesstype=resources.getStringArray(R.array.businesstype)
        val arrayAdapter1=
            ArrayAdapter(requireContext(),R.layout.textview_for_dorpdown_menu,businesstype)
        binding.sellectbussinesstype1.setAdapter(arrayAdapter1)

        //visible or invisible enter business type
        binding.sellectbussinesstype1.setOnItemClickListener { adapterView, view, i, l ->
            if(i==businesstype.size-1){
                binding.enterbusinesstype.visibility=View.VISIBLE
                binding.enterbusinesstype1.setOnFocusChangeListener { _, focused ->
                    if (!focused) {
                        if (binding.enterbusinesstype1.text.toString() == "") {
                            binding.enterbusinesstype.helperText = "required"
                        } else {
                            binding.enterbusinesstype.helperText = null
                        }
                    }
                }
            }
            else{
                binding.enterbusinesstype.visibility=View.GONE
            }

        }


        //required text on cname
        binding.cname1.setOnFocusChangeListener { _, focused ->
            if(!focused){
                if(binding.cname1.text.toString()==""){
                    binding.cname.helperText="required"
                }
                else{
                    binding.cname.helperText=null
                }
            }
        }

        //required text on cemail
        binding.cemail1.setOnFocusChangeListener { _, focused ->
            if(!focused){
                if(binding.cemail1.text.toString()==""){
                    binding.cemail.helperText="required"
                }
                else{
                    binding.cemail.helperText=null
                }
            }
        }

        //required text on cphone
        binding.cphone1.setOnFocusChangeListener { _, focused ->
            if(!focused){
                if(binding.cphone1.text.toString()==""){
                    binding.cphone.helperText="required"
                }
                else{
                    binding.cphone.helperText=null
                }
            }
        }

        //required text on company name
        binding.companyname1.setOnFocusChangeListener { _, focused ->
            if(!focused){
                if(binding.companyname1.text.toString()==""){
                    binding.companyname.helperText="required"
                }
                else{
                    binding.companyname.helperText=null
                }
            }
        }

        //domain
        binding.cdomain1.setOnFocusChangeListener { _, focused ->
            if(!focused){
                if(binding.cdomain1.text.toString()==""){
                    binding.cdomain.helperText="required"
                }
                else{
                    binding.cdomain.helperText=null
                }
            }
        }

        //address
        binding.address1.setOnFocusChangeListener { _, focused ->
            if(!focused){
                if(binding.address1.text.toString()==""){
                    binding.address.helperText="required"
                }
                else{
                    binding.address.helperText=null
                }
            }
        }

        //pincode
        binding.pincode1.setOnFocusChangeListener { _, focused ->
            if(!focused){
                if(binding.pincode1.text.toString()==""){
                    binding.pincode.helperText="required"
                }
                else{
                    binding.pincode.helperText=null
                }
            }
        }

        //required text on add on price
        binding.addonprice1.setOnFocusChangeListener { _, focused ->
            if(!focused){
                if(binding.addonprice1.text.toString()==""){
                    binding.addonprice.helperText="required"
                }
                else{
                    binding.addonprice.helperText=null
                }
            }
        }

        //required text on add on description
        binding.addondescription1.setOnFocusChangeListener { _, focused ->
            if(!focused){
                if(binding.addondescription1.text.toString()==""){
                    binding.addondescription.helperText="required"
                }
                else{
                    binding.addondescription.helperText=null
                }
            }
        }

        //submit button
        binding.b1.setOnClickListener {
            //null value to input fields
            emptyinputfields()
        }

        return binding.root
    }

    private fun emptyinputfields(){
        //blank values
        binding.cname1.text=null
        binding.cemail1.text=null
        binding.cphone1.text=null
        binding.companyname1.text=null
        binding.cdomain1.text=null
        binding.address1.text=null
        binding.pincode1.text=null
        binding.plane1.text=null
        binding.addonprice1.text=null
        binding.addondescription1.text=null
        binding.sellectbussinesstype1.text=null
        binding.enterbusinesstype1.text=null
    }

}