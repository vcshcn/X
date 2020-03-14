const path = require('path');

const HtmlWebPackPlugin = require("html-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const CleanWebpackPlugin = require('clean-webpack-plugin');

module.exports = (env, argv) => {
    return {
        entry: {
            main: path.join(__dirname, './src/index.js'),
            vendor: ['babel-polyfill'],
        },
        output: { 
            filename: '[name].[hash].bundle.js',
            chunkFilename: '[name].[hash].bundle.js', 
            path: path.resolve(__dirname, 'dist')
        }, 
        devServer: {
            port: 3000,
            publicPath: '/app',
            inline: true,
            hot: true, 
            historyApiFallback: true,
            proxy: {
                '/app/*.do': { 
                    target: 'http://localhost:8080/',
                    secure: false,
                    changeOrigin: true
                }
            }
        },
        module: {
            rules: [{
                    test: /\.(js|jsx)$/,
                    exclude: /node_modules/,
                    use: {
                        loader: "babel-loader"
                    }
                },
                {
                    test: /\.html$/,
                    use: [{
                        loader: "html-loader",
                        options: {
                            minimize: false
                        }
                    }]
                },
                {
                    test: /\.css$/,
                    use: [ 'style-loader', 'css-loader' ]
                },
                {
                    test: /\.less$/,
                    use: [
                        argv.mode !== 'production' ? 'style-loader' : MiniCssExtractPlugin.loader,
                        'css-loader',
                        'postcss-loader',
                        'less-loader',
                    ]
                },
                {
                    test: /\.(png|svg|jpg|gif|ico)$/,
                    use: [
                        'file-loader'
                    ]
                }
            ]
        },
        optimization: {
            splitChunks: {
                cacheGroups: {
                    commons: {
                        name: "commons",
                        chunks: "initial",
                        minChunks: 2
                    }
                }
            }
        },
        plugins: [
            new CleanWebpackPlugin(['dist']),
            new HtmlWebPackPlugin({
                template: "./public/index.html",
                filename: "./index.html",
                favicon: './public/images/favicon.ico',
                inject: true
            }),
            new MiniCssExtractPlugin({
                filename: "[name].css",
                chunkFilename: "[id].css"
            })
        ],
        resolve: {
            extensions: ['*', '.js', '.jsx', '.css', '.less']
        }
    }
}

