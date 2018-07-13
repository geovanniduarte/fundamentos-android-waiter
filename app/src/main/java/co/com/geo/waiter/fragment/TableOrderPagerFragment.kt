package co.com.geo.waiter.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

import co.com.geo.waiter.R
import co.com.geo.waiter.adapter.TableOrderPagerAdapter
import kotlinx.android.synthetic.main.fragment_table_order_pager.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TABLE = "ARG_TABLE"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TableOrderPagerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TableOrderPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TableOrderPagerFragment : Fragment() {
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param table tabla a mostrar inicialmente.
         * @return A new instance of fragment TableOrderPagerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(tableIndex: Int) =
                TableOrderPagerFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_TABLE, tableIndex)
                    }
                }
    }

    private var tableIndex: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tableIndex = it.getInt(ARG_TABLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_order_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        updateView()
    }

    private fun updateView() {
        val adapter = TableOrderPagerAdapter(fragmentManager!!)
        table_order_pager.adapter = adapter
    }

    fun moveToTableOrder(tableIndex: Int) {
        table_order_pager.currentItem = tableIndex
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_table_order_pager, menu)
    }

}
