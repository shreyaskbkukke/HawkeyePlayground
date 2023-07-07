package com.hawkeye.gameModule.ui.more.about

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hawkeye.BuildConfig
import com.hawkeye.R
import com.hawkeye.gameModule.ui.components.PreferenceRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navigateBack: () -> Unit,
    navigateOpenSourceLicenses: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.about_title)) },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_round_arrow_back_24),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                /*Icon(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                )*/
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Divider()

            PreferenceRow(
                title = stringResource(R.string.about_version),
                subtitle = BuildConfig.VERSION_NAME,
                painter = painterResource(R.drawable.ic_outline_info_24),
            )

            val uriHandler = LocalUriHandler.current
            PreferenceRow(
                title = stringResource(R.string.about_github_project),
                painter = painterResource(R.drawable.ic_github_24dp),
                onClick = {
                    uriHandler.openUri("https://github.com/shreyaskbkukke/hawkeye_playground")
                }
            )

            PreferenceRow(
                title = stringResource(R.string.contact_us),
                painter = painterResource(R.drawable.baseline_mail_outline_24),
                onClick = {
                    uriHandler.openUri("mailto:shreyaskb333@gmail.com")
                }
            )

            PreferenceRow(
                title = stringResource(R.string.help_translate),
                painter = painterResource(R.drawable.baseline_translate_24),
                onClick = {
                    uriHandler.openUri("https://www.linkedin.com/in/shreyaskb333/")
                }
            )

            PreferenceRow(
                title = stringResource(R.string.libraries_licenses_title),
                painter = painterResource(R.drawable.ic_outline_info_24),
                onClick = navigateOpenSourceLicenses
            )
        }
    }
}