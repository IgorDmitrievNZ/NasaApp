import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.BottomNavigationLayoutBinding
import com.example.android.nasaapp.ui.chips.ChipsFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationLayoutBinding? = null
    val binding: BottomNavigationLayoutBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.navigation_one -> {
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
                }
                R.id.navigation_two -> {
                    Toast.makeText(context,"2",Toast.LENGTH_SHORT).show()
                }
                R.id.navigation_lesson_one -> {
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,
                ChipsFragment.newInstance()).commit()
                }
            }

            true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}