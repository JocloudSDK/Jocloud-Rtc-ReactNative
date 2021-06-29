
export class VideoPosition {
    index = 0;  // 单个视频视图布局座位信息
    x = 0;  // 单个视频视图布局x轴坐标
    y = 0;  // 单个视频视图布局y轴坐标
    width = 0;  // 单个视频视图宽度
    height = 0;  // 单个视频视图高度

    toMap() {
        return {
            'index': item.index,
            'x': item.x,
            'y': item.y,
            'width': item.width,
            'height': item.height,
        }
    }
}