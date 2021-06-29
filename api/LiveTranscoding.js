const TAG_TRANSCODINGMODE = "mTransCodingMode";
const TAG_AUDIOURL = "mAudioUrl";
const TAG_LYRICURL = "mLyricUrl";
const TAG_MEDIAURL = "mMediaUrl";
const TAG_MEDIASTREAMLAYOUT = "mMediaStreamLayout";
const TAG_LAYOUTX = "layoutX";
const TAG_LAYOUTY = "layoutY";
const TAG_LAYOUTW = "layoutW";
const TAG_LAYOUTH = "layoutH";
const TAG_ZORDER = "zOrder";
const TAG_BCROP = "bCrop";
const TAG_CROPX = "cropX";
const TAG_CROPY = "cropY";
const TAG_CROPW = "cropW";
const TAG_CROPH = "cropH";
const TAG_ALPHA = "alpha";
const TAG_USERS = "mUserList";
const TAG_UID = "uid";
const TAG_ROOMID = "roomId";
const TAG_BSTANDARD = "bStandard";
const TAG_AUDIOROOM = "audioRoom";

export class LiveTranscoding {
    transCodingMode = 0
    audioUrl = ""
    lyricUrl = ""
    mediaUrl = ""
    mediaStreamLayout = null
    userList = []

    getMediaStreamLayoutMap() {
        if (this.mediaStreamLayout == null) {
            return {}
        }
        return {
            'layoutX': this.mediaStreamLayout.layoutX,
            'layoutY': this.mediaStreamLayout.layoutY,
            'layoutW': this.mediaStreamLayout.layoutW,
            'layoutH': this.mediaStreamLayout.layoutH,
            'layoutX': this.mediaStreamLayout.layoutX,
            'zOrder': this.mediaStreamLayout.zOrder,
            'bCrop': this.mediaStreamLayout.bCrop,
            'cropX': this.mediaStreamLayout.cropX,
            'cropY': this.mediaStreamLayout.cropY,
            'cropW': this.mediaStreamLayout.cropW,
            'cropH': this.mediaStreamLayout.cropH,
            'alpha': this.mediaStreamLayout.alpha,
        }
    }

    getUserListMap() {
        var ret = [];
        this.userList.map((item, i) => {
            ret.push({
                'uid':item.uid,
                'roomId':item.roomId,
                'bStandard':item.bStandard,
                'layoutX':item.layoutX,
                'layoutY':item.layoutY,
                'layoutW':item.layoutW,
                'layoutH':item.layoutH,
                'zOrder':item.zOrder,
                'bCrop':item.bCrop,
                'cropX':item.cropX,
                'cropY':item.cropY,
                'cropW':item.cropW,
                'cropH':item.cropH,
                'alpha':item.alpha,
                'audioRoom':item.audioRoom,
            })
        })
        return ret;
    }
}

export class MediaStreamLayout {
    layoutX = 0
    layoutY = 0
    layoutW = 0
    layoutH = 0
    zOrder = 0
    bCrop = true
    cropX = 0
    cropY = 0
    cropW = 0
    cropH = 0
    alpha = 0
}

export class TranscodingUser {
    uid = ""
    roomId = ""
    bStandard = false
    layoutX = 0
    layoutY = 0
    layoutW = 0
    layoutH = 0
    zOrder = 0
    bCrop = true
    cropX = 0
    cropY = 0
    cropW = 0
    cropH = 0
    alpha = 0
    audioRoom = 0
}