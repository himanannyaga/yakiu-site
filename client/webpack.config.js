const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {
	entry: "./src/main.ts",
	output: {
    path:"./dist",
    filename: 'main.js'
  },
	resolve: {
		extensions: ['', '.ts','.js']
  },
	module: {
    loaders: [
      {
        test: /\.ts$/,
        loader: 'awesome-typescript-loader'
      },
			{ test: /\.html$/, loader: 'raw'},
			//scssインポートするとrawで
			{test: /\.scss$/, loaders: ["raw", "sass"]},
    ]
  },
		plugins: [
		//index.htmlコピー
		new CopyWebpackPlugin([
			{ from: 'src/index.html'}
		])
	],
}
