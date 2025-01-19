package br.com.hellodev.design.presenter.components.bottom.sheet.default

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ShapeBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        shape = ShapeBottomSheet,
        containerColor = HelloTheme.colorScheme.screen.backgroundSecondary,
        dragHandle = {},
        content = content
    )
}