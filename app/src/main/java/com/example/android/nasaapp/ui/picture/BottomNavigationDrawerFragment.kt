import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.BottomNavigationLayoutBinding
import com.example.android.nasaapp.ui.chips.ChipsFragment
import com.example.android.nasaapp.ui.lesson3_bot_nav__tab_layout.NavBottomActivity
import com.example.android.nasaapp.ui.lesson3_bot_nav__tab_layout.ViewPagerActivity
import com.example.android.nasaapp.utils.openFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private val backStackName: String = "BottomNavigationDrawerFragment"

    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding: BottomNavigationLayoutBinding
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
                    startActivity(Intent(requireContext(), NavBottomActivity::class.java))
                }
                R.id.navigation_two -> {
                    startActivity(Intent(requireContext(), ViewPagerActivity::class.java))
                }
                R.id.navigation_lesson_one -> {
                    openFragment(requireActivity(), ChipsFragment.newInstance(), backStackName)
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