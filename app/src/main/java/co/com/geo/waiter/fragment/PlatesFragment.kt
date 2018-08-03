package co.com.geo.waiter.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import co.com.geo.waiter.R
import co.com.geo.waiter.adapter.PlatesReciclerViewAdapter
import co.com.geo.waiter.model.Plate
import co.com.geo.waiter.model.Plates
import co.com.geo.waiter.model.Table
import co.com.geo.waiter.model.Tables
import kotlinx.android.synthetic.main.fragment_plates.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlatesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PlatesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlatesFragment : Fragment() {

    companion object {

        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_TABLE_INDEX = "ARG_TABLE_INDEX"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param order Order if this fragment list represents an order.
         * @return A new instance of fragment PlatesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(tableIndex: Int) =
                PlatesFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_TABLE_INDEX, tableIndex)
                    }
                }
    }

    private var tableIndex: Int = 0
    private var table: Table? = null
    private var listener: OnPlatesFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tableIndex = it.getInt(ARG_TABLE_INDEX)
            table = Tables[tableIndex]
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: si hay TableOrder hay que listar los platos de tableOrder, si no entonces se listan todos los platos.
        var adapter: PlatesReciclerViewAdapter? = null
        if (table == null) {
            val plates = Plates.getPlates()
            adapter = PlatesReciclerViewAdapter(plates)
        } else {
            if (table!!.tableOrder != null) {
                adapter = PlatesReciclerViewAdapter(table!!.tableOrder!!.getPlates())
            }
        }
        if (adapter != null) {
            adapter.onClickListener = View.OnClickListener {
                Toast.makeText(activity, "pulsado plato", Toast.LENGTH_LONG).show()
            }
        }

        plates_list.layoutManager = LinearLayoutManager(activity!!)
        plates_list.adapter = adapter

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPlatesFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnPlatesFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onPlateSelected(plate: Plate)
    }
}
