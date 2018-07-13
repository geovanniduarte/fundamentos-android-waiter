package co.com.geo.waiter.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import co.com.geo.waiter.R
import co.com.geo.waiter.model.Table
import co.com.geo.waiter.model.Tables
import kotlinx.android.synthetic.main.fragment_tables.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [TablesFragment.OnTableSelectedListener] interface.
 */
class TablesFragment : Fragment() {

    companion object {

        // Parameters
        const val ARG_COLUMN_COUNT = "column-count"

        // Instance pattern
        @JvmStatic
        fun newInstance(columnCount: Int) =
                TablesFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnTableSelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tables, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter<Table>(activity,
                                          android.R.layout.simple_list_item_1,
                                          Tables.toArray()
        )

        table_list.setOnItemClickListener { parent, view, position, id ->
            var table = Tables[position]
            listener?.onTableSelected(position)
        }

        table_list.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTableSelectedListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnTableSelectedListener")
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
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnTableSelectedListener {
        fun onTableSelected(position: Int?)
    }
}
