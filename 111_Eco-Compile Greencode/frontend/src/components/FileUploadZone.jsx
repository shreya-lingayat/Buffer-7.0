import React, { useRef } from 'react';
import { Upload, FileCode, Loader2 } from 'lucide-react';
import { motion } from 'framer-motion';

const FileUploadZone = ({ onFileSelect, loading }) => {
  const fileInputRef = useRef(null);

  const handleDragOver = (e) => {
    e.preventDefault();
    e.stopPropagation();
  };

  const handleDrop = (e) => {
    e.preventDefault();
    e.stopPropagation();
    const files = e.dataTransfer.files;
    if (files.length > 0) processFile(files[0]);
  };

  const processFile = (file) => {
    if (file.name.endsWith('.java')) {
      onFileSelect(file);
    } else {
      alert('Please upload a .java file.');
    }
  };

  return (
    <motion.div 
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      className="max-w-xl w-full"
    >
      <div
        onDragOver={handleDragOver}
        onDrop={handleDrop}
        onClick={() => fileInputRef.current.click()}
        className="glass rounded-2xl p-12 flex flex-col items-center justify-center border-2 border-dashed border-emerald-500/30 hover:border-emerald-500/60 transition-all cursor-pointer group"
      >
        <input
          type="file"
          ref={fileInputRef}
          onChange={(e) => processFile(e.target.files[0])}
          className="hidden"
          accept=".java"
        />
        
        {loading ? (
          <div className="flex flex-col items-center">
            <Loader2 className="w-12 h-12 text-emerald-500 animate-spin mb-4" />
            <p className="text-emerald-100 text-lg font-medium">Analyzing Footprint...</p>
          </div>
        ) : (
          <>
            <div className="w-16 h-16 bg-emerald-500/10 rounded-full flex items-center justify-center mb-6 group-hover:scale-110 transition-transform">
              <Upload className="text-emerald-500 w-8 h-8" />
            </div>
            <h3 className="text-2xl font-bold mb-2">Identify your emissions</h3>
            <p className="text-slate-400 text-center">
              Drag and drop your <span className="text-emerald-400 font-mono">.java</span> file here <br/>
              to calculate your Green Score.
            </p>
          </>
        )}
      </div>
    </motion.div>
  );
};

export default FileUploadZone;
