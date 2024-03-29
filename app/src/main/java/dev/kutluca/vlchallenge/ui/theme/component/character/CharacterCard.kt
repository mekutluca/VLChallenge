package dev.kutluca.vlchallenge.ui.theme.component.character

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import dev.kutluca.vlchallenge.model.presentation.character.CharacterStatus
import dev.kutluca.vlchallenge.model.presentation.character.CharacterUiModel
import dev.kutluca.vlchallenge.util.compose.swipable

@Composable
fun CharacterCard(
    character: CharacterUiModel,
    onCharacterSwipedLeft: () -> Unit,
    onCharacterSwipedRight: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .swipable(
                onSwipedLeft = onCharacterSwipedLeft,
                onSwipedRight = onCharacterSwipedRight,
            )
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(24.dp)
    ) {
        GlideImage(
            imageModel = { character.imageUrl },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .aspectRatio(1f),
        )
        Text(
            text = character.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            style = MaterialTheme.typography.headlineLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(imageVector = Icons.Rounded.Place, contentDescription = null)
            Text(
                text = character.location,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            when (character.status) {
                CharacterStatus.ALIVE -> Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null
                )

                CharacterStatus.DEAD -> Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = null
                )

                CharacterStatus.UNKNOWN -> Icon(
                    imageVector = Icons.Outlined.Warning,
                    contentDescription = null
                )
            }
            Text(
                text = character.status.text,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )
        }

    }
}