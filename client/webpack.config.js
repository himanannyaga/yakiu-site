module.exports = {
	entry: "./src/main.ts",
	output: {
    path:"./dist",
    filename: 'main.js'
  },
	resolve: {
		extensions: ['', '.ts','.js'],
		root:["./src"]
  },
	module: {
    loaders: [
      {
        test: /\.ts$/,
        loader: 'awesome-typescript-loader'
      },
			{ test: /\.html$/, loader: 'raw'},
			{ test: /\.scss$/, loaders: ["raw","sass"] },
			{ test: /\.png$/, loader: "url" }
    ]
  }
};
