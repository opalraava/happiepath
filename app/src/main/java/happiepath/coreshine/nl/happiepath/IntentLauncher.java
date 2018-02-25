package happiepath.coreshine.nl.happiepath;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class IntentLauncher {
    Context context;

    public IntentLauncher(Context context) {
        this.context = context;
    }

    // youtube

    public void youtubeWatchPlaylist(String id_list) {
        openWebPage(String.format("https://www.youtube.com/watch?list=%s", id_list));
    }

    public void youtubeWatchVideo(String id_video) {
        openWebPage(String.format("https://www.youtube.com/watch?v=%s", id_video));
    }

    public void youtubeDisplayUserpage(String user) {
        openWebPage(String.format("https://www.youtube.com/user/%s", user));
    }

    public void youtubeDisplayChannel(String id_channel) {
        openWebPage(String.format("https://www.youtube.com/channel/%s", id_channel));
    }

    public void youtubeSearch(String query) {
        openWebPage(String.format("https://www.youtube.com/results?search_query=%s", query));
    }

    // spotify

    public void spotifyListenPlaylist(String playlist_owner, String id_playlist) {
        openWebPage(String.format("spotify:user:%s:playlist:%s:play", playlist_owner, id_playlist));
    }

    public void spotifyListenTrack(String id_track) {
        openWebPage(String.format("spotify:track:%s", id_track));
    }

    public void spotifyListenAlbum(String id_album) {
        openWebPage(String.format("spotify:album:%s:play", id_album));
    }

    public void spotifyDisplayArtist(String id_artist) {
        openWebPage(String.format("spotify:artist:%s", id_artist));
    }


    // These below are taken from the documentation. There is more to find there...
    // https://developer.android.com/guide/components/intents-common.html

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
