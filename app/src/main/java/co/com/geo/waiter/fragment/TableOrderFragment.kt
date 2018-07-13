package co.com.geo.waiter.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.com.geo.waiter.R
import co.com.geo.waiter.model.*
import kotlinx.android.synthetic.main.fragment_table_order.*
import java.util.*

private const val ARG_TABLE_INDEX = "ARG_TABLE_INDEX"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OrderFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TableOrderFragment : Fragment() {

    companion object {
        fun newInstance(tableIndex: Int): TableOrderFragment {
            val arguments = Bundle()
            arguments.putInt(ARG_TABLE_INDEX, tableIndex)
            val fragment = TableOrderFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    private var tableIndex: Int? = null
    var table : Table? = null
    set(value) {
        field = value
        updateView(value)
    }

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tableIndex = it.getInt(ARG_TABLE_INDEX, 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        table = Tables[tableIndex!!]
        updateView(table)

        if (fragmentManager?.findFragmentById(R.id.order_plates_list_fragment) == null) {
            val platesFragment = PlatesFragment.newInstance(tableIndex!!)
            fragmentManager?.beginTransaction()!!
                    .add(R.id.order_plates_list_fragment, platesFragment)
                    .commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun getUserVisibleHint(): Boolean {
        super.getUserVisibleHint()
        updateView(table)
        return true
    }

    fun updateView(table: Table?) {
        table_number.text = table?.number.toString()
        table_room.text = table?.roomName
    }

    fun moveToTable(tableIndex: Int) {

    }

    fun beginOrdering() {

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
    interface OnFragmentInteractionListener {
        fun onNewPlateAdded(Plate: Plate)
    }
}
